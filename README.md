# Task Manager

A Spring Boot task management API with a Postgres-backed datastore, managed via Flyway migrations.

## Prerequisites

Install these before you start:

1. **Java 21**
   - macOS (Homebrew): `brew install openjdk@21`
   - Verify: `java -version` should print `21.x`

   > A Maven install is **not** required — this project ships the Maven Wrapper (`./mvnw`), which downloads the correct Maven version automatically the first time you run it.

2. **Docker**, to run Postgres locally. On macOS there are two common options:
   - **Docker Desktop** (simplest — bundles the daemon, CLI, and Compose plugin): download from https://www.docker.com/products/docker-desktop
   - **Colima** (lightweight, no GUI/license, pairs with the Homebrew `docker` CLI):
     ```bash
     brew install colima docker docker-compose
     colima start
     ```
     If `docker compose` isn't found after installing, register the plugin once:
     ```bash
     mkdir -p ~/.docker
     cat > ~/.docker/config.json << 'EOF'
     {
       "cliPluginsExtraDirs": [
         "/opt/homebrew/lib/docker/cli-plugins"
       ]
     }
     EOF
     ```

   Verify Docker works: `docker info` should show a `Server` section (not a connection error), and `docker compose version` should print a version.

## Setup

1. **Clone the repository** and enter the project directory:
   ```bash
   git clone <repository-url>
   cd taskmanager
   ```

2. **Start a local Postgres database** via Docker Compose (config: [docker-compose.yml](docker-compose.yml)):
   ```bash
   docker compose up -d
   ```
   This starts Postgres 17 on `localhost:5432` with database `taskmanager`, user `taskmanager`, password `taskmanager` (local-only defaults — not used anywhere sensitive). Data persists in a Docker volume across restarts.

   To stop it later: `docker compose down` (add `-v` to also wipe the data volume and start fresh next time).

3. **Run the application** using the `local` Spring profile, which points at the container from step 2 (see [application-local.properties](src/main/resources/application-local.properties)):
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
   ```
   On startup, Flyway automatically applies the SQL migrations in [src/main/resources/db/migration](src/main/resources/db/migration), creating the `USER_INFO` and `TASK` tables (plus its own `flyway_schema_history` tracking table).

4. **Verify it's running**: the app starts on `http://localhost:8080` by default. You can also inspect the database directly:
   ```bash
   docker exec -it taskmanager-postgres psql -U taskmanager -d taskmanager -c '\dt'
   ```
   You should see `user_info`, `task`, and `flyway_schema_history` listed.

## Running tests

```bash
./mvnw test
```

## Using a remote database instead (e.g. Supabase)

The default profile (`application.properties`) is configured to connect to a remote Postgres instance (such as Supabase) via environment variables instead of the local Docker container. Set these before running without `-Dspring-boot.run.profiles=local`:

```bash
export SUPABASE_DB_HOST=<your-host>          # e.g. aws-0-<region>.pooler.supabase.com
export SUPABASE_DB_USER=<your-user>          # e.g. postgres.<project-ref>
export SUPABASE_DB_PASSWORD=<your-password>
# Optional, default to 5432 / postgres if unset:
export SUPABASE_DB_PORT=5432
export SUPABASE_DB_NAME=postgres

./mvnw spring-boot:run
```

Get these values from your Supabase project's **Settings → Database → Connection string** (use the **Session pooler**, port `5432` — not the Transaction pooler on `6543`, which doesn't support Flyway's session-level locking or Hibernate's prepared-statement caching correctly).

### Testing the connection independently

Before running the full app, you can verify the DB credentials alone using a throwaway container with `psql` — no local Postgres client install needed:

```bash
docker run --rm -e PGPASSWORD=<your-password> postgres:17 \
  psql -h <your-host> -p 5432 -U <your-user> -d postgres \
  -c "SELECT version();"
```

A version string back means the host, port, and credentials are all correct. This isolates "is it the database" from "is it my app config" — useful whenever the app itself fails to start with a connection error.

## Sharing database credentials across a team

Two different things need sharing, and they go through different channels:

- **Local Postgres setup** — nothing to share. [docker-compose.yml](docker-compose.yml) is committed with fixed dev-only credentials, so every developer runs `docker compose up -d` and gets an identical, isolated local database. No secrets involved.

- **Remote Supabase credentials** — these are real secrets and must never go through git, Slack, or email in plaintext. Recommended setup:
  1. **Copy the template**: `cp .env.example .env`, then fill in the values below.
  2. **Get the values securely**: add each teammate as a member of the Supabase project (Project Settings → Team) so they can retrieve the current host/user/password themselves from the dashboard, rather than someone pasting them into a chat message.
  3. **For the DB password specifically** (Supabase dashboard access doesn't always hand this out post-creation, e.g. after a reset), share it via a password manager's shared vault (1Password, Bitwarden, etc.) — never a message that stays in plaintext history.
  4. If a password was ever shared insecurely (chat, email, a screenshot), rotate it in Supabase afterwards (Settings → Database → Reset database password) and redistribute the new one properly.

`.env` is git-ignored on purpose — each developer keeps their own local copy; `.env.example` (committed) documents which keys are needed without exposing any values.

## Project structure

- `src/main/java` — application code (entities, repositories, services, controllers)
- `src/main/resources/db/migration` — Flyway SQL migrations (source of truth for the schema)
- `src/main/resources/application.properties` — base config (remote/Supabase datasource)
- `src/main/resources/application-local.properties` — local Docker Postgres overrides
- `docker-compose.yml` — local Postgres container definition
- `.env.example` — template listing the env vars needed for a remote (e.g. Supabase) connection; copy to `.env` and fill in real values (git-ignored)

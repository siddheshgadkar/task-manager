CREATE TABLE USER_INFO(
    guid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    created_date TIMESTAMPTZ NOT NULL,
    updated_date TIMESTAMPTZ NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE TASK(
    guid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    assignee_id UUID CONSTRAINT fk_task_assignee REFERENCES USER_INFO(guid) ON DELETE SET NULL,
    version BIGINT NOT NULL DEFAULT 0,
    created_date  TIMESTAMPTZ NOT NULL,
    updated_date TIMESTAMPTZ NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    parent_task_id UUID CONSTRAINT fk_task_parent_task_id REFERENCES TASK(guid)
);
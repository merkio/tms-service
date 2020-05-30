CREATE SCHEMA IF NOT EXISTS tms_core;

CREATE TABLE IF NOT EXISTS tms_core.acceptance_criteria
(
    id                         BIGSERIAL NOT NULL,
    feature_id                 BIGINT,
    name                       VARCHAR,
    description                VARCHAR,
    created_by                 BIGINT,
    created_on                 TIMESTAMP WITH TIME ZONE,
    updated_on                 TIMESTAMP WITH TIME ZONE,
    order_id                   INTEGER
);

CREATE TABLE IF NOT EXISTS tms_core.favourite_projects
(
    user_id    BIGINT,
    project_id BIGINT
);

CREATE TABLE IF NOT EXISTS tms_core.feature_details
(
    id                        BIGSERIAL NOT NULL,
    feature_id                BIGINT,
    qa_engineers              VARCHAR,
    team_name                 VARCHAR,
    feature_starts_on         TIMESTAMP WITH TIME ZONE,
    test_cases_ready_on       TIMESTAMP WITH TIME ZONE,
    automation_tests_ready_on TIMESTAMP WITH TIME ZONE,
    testing_starts_on         TIMESTAMP WITH TIME ZONE,
    release_on                TIMESTAMP WITH TIME ZONE,
    created_on                TIMESTAMP WITH TIME ZONE,
    updated_on                TIMESTAMP WITH TIME ZONE,
    jira_label                VARCHAR,
    report_recipients         VARCHAR,
    sign_off                  BOOLEAN,
    sign_off_by               BIGINT,
    sign_off_on               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS tms_core.feature_specs
(
    id         BIGSERIAL NOT NULL,
    version    INTEGER,
    feature_id BIGINT,
    url        VARCHAR,
    content_id VARCHAR,
    source     VARCHAR,
    type       VARCHAR,
    created_on TIMESTAMP WITH TIME ZONE,
    updated_on TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS tms_core.features
(
    id                         BIGSERIAL NOT NULL,
    name                       VARCHAR,
    project_id                 BIGINT,
    created_by                 BIGINT,
    released                   BOOLEAN,
    released_on                TIMESTAMP WITH TIME ZONE,
    details_id                 BIGINT,
    product_specs              VARCHAR,
    tech_specs                 VARCHAR,
    test_strategy              VARCHAR,
    business_domain            VARCHAR,
    business_area              VARCHAR,
    parent_id                  BIGINT,
    automation_application_map BOOLEAN,
    created_on                 TIMESTAMP WITH TIME ZONE,
    updated_on                 TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS tms_core.projects
(
    id                     BIGSERIAL NOT NULL,
    name                   TEXT,
    tabs                   VARCHAR,
    description            VARCHAR,
    image_path             VARCHAR,
    test_story_template    VARCHAR,
    test_strategy_template VARCHAR,
    type                   VARCHAR,
    created_on             TIMESTAMP WITH TIME ZONE,
    updated_on             TIMESTAMP WITH TIME ZONE
);


CREATE TABLE IF NOT EXISTS tms_core.test_stories
(
    id                     BIGSERIAL NOT NULL,
    title                  VARCHAR   NOT NULL,
    description            text,
    acceptance_criteria_id BIGINT,
    priority               INT,
    automated              BOOLEAN,
    labels                 VARCHAR,
    created_by             BIGINT,
    created_on             TIMESTAMP WITH TIME ZONE,
    updated_on             TIMESTAMP WITH TIME ZONE,
    feature_id             BIGINT,
    order_id               INTEGER,
    automation_template    TEXT,
    type                   VARCHAR DEFAULT 'MANUAL'::VARCHAR
);

ALTER TABLE ONLY tms_core.acceptance_criteria
    ADD CONSTRAINT acceptance_criteria_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tms_core.feature_details
    ADD CONSTRAINT feature_details_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tms_core.feature_specs
    ADD CONSTRAINT feature_specs_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tms_core.features
    ADD CONSTRAINT features_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tms_core.projects
    ADD CONSTRAINT projects_pkey PRIMARY KEY (id);

CREATE INDEX acceptance_criteria_feature_id_index ON tms_core.acceptance_criteria USING btree (feature_id);

CREATE INDEX feature_details_feature_id_index ON tms_core.feature_details USING btree (feature_id);

CREATE INDEX project_id_idx ON tms_core.favourite_projects USING btree (project_id);

CREATE INDEX user_id_idx ON tms_core.favourite_projects USING btree (user_id);

ALTER TABLE ONLY tms_core.favourite_projects
    ADD CONSTRAINT favourite_projects_project_id_fkey FOREIGN KEY (project_id) REFERENCES tms_core.projects (id);

ALTER TABLE ONLY tms_core.feature_specs
    ADD CONSTRAINT feature_specs_feature_id_fkey FOREIGN KEY (feature_id) REFERENCES tms_core.features (id);

ALTER TABLE ONLY tms_core.features
    ADD CONSTRAINT features_details_id_fkey FOREIGN KEY (details_id) REFERENCES tms_core.feature_details (id);

ALTER TABLE ONLY tms_core.test_stories
    ADD CONSTRAINT fk_ac_test_stories FOREIGN KEY (acceptance_criteria_id) REFERENCES tms_core.acceptance_criteria (id) ON DELETE CASCADE;

ALTER TABLE ONLY tms_core.acceptance_criteria
    ADD CONSTRAINT fk_feature_acceptance_criteria FOREIGN KEY (feature_id) REFERENCES tms_core.features (id) ON DELETE CASCADE;


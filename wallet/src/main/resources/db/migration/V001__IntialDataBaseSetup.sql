CREATE TABLE earnings (
  job_id            BIGINT NOT NULL,
  fee               INT NOT NULL,
  tip               INT NOT NULL,
  bonus             INT NOT NULL,
  adjustments       INT NOT NULL,
  subsidies         INT NOT NULL,
  job_status        VARCHAR(32) NOT NULL,
  employee_id       VARCHAR(40) NOT NULL,
  version           DATETIME NOT NULL,
  PRIMARY KEY (job_id)
);
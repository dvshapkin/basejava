create table resume
(
  full_name varchar not null,
  uuid      char(36) not null
    constraint resume_pkey
    primary key
);

create table contact
(
  id          serial  not null
    constraint contact_pkey
    primary key,
  type        varchar not null,
  value       varchar not null,
  resume_uuid char(36)
    constraint contact_resume_uuid_fk
    references resume
    on delete cascade
);

CREATE UNIQUE INDEX contact_uuid_type_index ON public.contact (resume_uuid, type);
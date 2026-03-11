create extension if not exists "pgcrypto";

create type PRIORITY_TYPE as enum (
    'HIGH',
    'MEDIUM',
    'LOW'
);

create type TASK_STATUS as enum (
    'IN_PROGRESS',
    'COMPLETED'
);

create table credentials (
    id uuid primary key default gen_random_uuid(),
    email text unique not null,
    password text not null,

    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table users(
    id uuid primary key default gen_random_uuid(),
    name text not null,
    credential_id uuid not null unique,

    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),

    constraint fk_users_credentials
        foreign key(credential_id)
        references credentials(id)
        on delete restrict
);

create table tasks (
    id uuid primary key default gen_random_uuid(),
    task_number bigserial not null unique,
    title text not null,
    description text not null,
    user_id uuid not null,
    priority PRIORITY_TYPE not null,
    status TASK_STATUS not null default 'IN_PROGRESS',
    deadline date not null,

    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,

    constraint fk_tasks_users
        foreign key (user_id)
        references users(id)
        on delete restrict
);

create index idx_tasks_user_id on tasks(user_id);
create index idx_tasks_status on tasks(status);
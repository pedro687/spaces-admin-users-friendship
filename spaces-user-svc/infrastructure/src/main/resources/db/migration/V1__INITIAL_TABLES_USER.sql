CREATE TABLE IF NOT EXISTS tb_users_data(
    id bigint(20) auto_increment primary key,
    uuid varchar(36) not null unique,
    username varchar(20) not null unique,
    password varchar(255) not null,
    email varchar(255) unique not null,
    birthday_date DATE not null,
    gender enum('MAN', 'WOMAN', 'OTHER') not null,
    active tinyint(1) not null,
    tellphone varchar(13) not null unique,
    image_location varchar(300) default null,
    created_at datetime(6),
    updated_at datetime(6),
    deleted_at datetime(6)
);

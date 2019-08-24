create sequence product_id_seq start with 1 increment by 1;
create sequence order_id_seq start with 1 increment by 1;
create sequence order_item_id_seq start with 1 increment by 1;

create table products (
    id bigint DEFAULT nextval('product_id_seq') not null,
    code varchar(255) not null CONSTRAINT product_code_unique UNIQUE,
    name varchar(255) not null,
    description varchar(255),
    price numeric not null,
    created_at timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp WITH TIME ZONE,
    primary key (id)
);

create table orders (
    id bigint DEFAULT nextval('order_id_seq') not null,
    order_id varchar(100),
    customer_email varchar(100),
    customer_name varchar(100),
    delivery_address varchar(255),
    credit_card_number varchar(50),
    cvv varchar(10),
    status varchar(100),
    created_at timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp WITH TIME ZONE,
    primary key (id)
);

create table order_items (
    id bigint DEFAULT nextval('order_item_id_seq') not null,
    product_code varchar(255) not null,
    product_price numeric not null,
    quantity integer not null,
    order_id bigint not null references orders(id),
    created_at timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp WITH TIME ZONE,
    primary key (id)
);

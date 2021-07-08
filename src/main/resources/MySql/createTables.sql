use `codexShopDB`;

CREATE TABLE `user`(
    `id` INTEGER not null auto_increment,
    `nickname` varchar(100) not null,
    `email` varchar(255) not null ,
    `password` varchar(255) not null ,
    `status` enum ('ADMIN', 'USER') not null,

    PRIMARY KEY(`id`),
    UNIQUE (id),
    UNIQUE (email)
);

CREATE TABLE `product` (
    `id` INTEGER not null auto_increment,
    `name` varchar(255) not null,
    `description` text not null,

    PRIMARY KEY(`id`),
    UNIQUE (`id`)
);

CREATE TABLE `cart` (
    `id` INTEGER not null auto_increment,
    `user_id` INTEGER not null,

    PRIMARY KEY(`id`),
    UNIQUE (`id`)
);

CREATE TABLE `order_item` (
    `cart_id` INTEGER not null,
    `product_id` INTEGER not null,

    FOREIGN KEY (`cart_id`) references `cart` (`id`),
    FOREIGN KEY (`product_id`) references `product` (`id`)
);

CREATE TABLE `tag` (
    `id` INTEGER not null auto_increment,
    `value` varchar(255) not null,

    PRIMARY KEY (`id`),
    UNIQUE (`id`)
);

CREATE TABLE `tag_product` (
    `product_id` INTEGER  not null,
    `tag_id` INTEGER not null,

    FOREIGN KEY (`product_id`) references `product` (`id`),
    FOREIGN KEY (`tag_id`) references `tag` (`id`)
);

CREATE TABLE `person`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(50) NOT NULL,
    `position` ENUM ('DOCTOR', 'NURSE', 'ASSISTANT', 'THERAPIST'),
    CONSTRAINT pk_person PRIMARY KEY (`id`)
)
    CHARACTER SET utf8
    COLLATE utf8_unicode_ci;

CREATE TABLE `room`
(
    `id`     INT NOT NULL AUTO_INCREMENT,
    `number` INT NOT NULL,
    `type`   ENUM ('THERAPY_ROOM', 'TRAINING_ROOM', 'OPERATING_ROOM', 'LABORATORY'),
    CONSTRAINT pk_room PRIMARY KEY (`id`)
)
    CHARACTER SET utf8
    COLLATE utf8_unicode_ci;

CREATE TABLE `reservation`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `room_id`     INT          NOT NULL,
    `person_id`   INT          NOT NULL,
    `name`        VARCHAR(255) NOT NULL,
    `description` VARCHAR(5000),
    `start_date`  TIMESTAMP    NOT NULL DEFAULT UTC_TIMESTAMP,
    `end_date`    TIMESTAMP    NOT NULL DEFAULT UTC_TIMESTAMP,

    CONSTRAINT pk_reservation PRIMARY KEY (`id`),
    CONSTRAINT fk_reservation_room_id_room_id FOREIGN KEY (`room_id`) REFERENCES `room` (id) ON DELETE CASCADE,
    CONSTRAINT fk_reservation_person_id_person_id FOREIGN KEY (`person_id`) REFERENCES `person` (id),
    INDEX ix_reservation_room_id (room_id),
    INDEX ix_reservation_person_id (person_id),
    CHECK ( start_date < end_date )
)
    CHARACTER SET utf8
    COLLATE utf8_unicode_ci;

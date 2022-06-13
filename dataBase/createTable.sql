CREATE TABLE `alianza`.`satelite` (
  `nombre` varchar(45) NOT NULL,
  `X` int NOT NULL,
  `Y` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `alianza`.`satelite`
(`nombre`,
`X`,
`Y`)
VALUES
('Kenobi',-500,-200);

INSERT INTO `alianza`.`satelite`
(`nombre`,
`X`,
`Y`)
VALUES
('Skywalker',100,-100);

INSERT INTO `alianza`.`satelite`
(`nombre`,
`X`,
`Y`)
VALUES
('Sato',500,100);

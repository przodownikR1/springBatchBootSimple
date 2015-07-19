--DROP TABLE person IF EXISTS;

CREATE TABLE `person` (
  `id`  mediumint(8) unsigned NOT NULL auto_increment,
  `login` varchar(255) default NULL,
  `passwd` varchar(255),
  `age` mediumint default NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (1,"Sparks","3347",74),(2,"Doyle","6569",42),(3,"Gardner","3374",100),(4,"Maddox","2318",44),(5,"Mullen","2497",44),(6,"Salazar","9193",53),(7,"Farrell","2617",87),(8,"Grant","9674",21),(9,"Acosta","5963",13),(10,"Schmidt","3107",46);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (11,"Hogan","8024",61),(12,"Perkins","3379",94),(13,"Weeks","3806",44),(14,"Robbins","8827",94),(15,"Mullins","1833",78),(16,"Peterson","8328",37),(17,"Carson","5044",19),(18,"Guerra","5530",73),(19,"Ross","2330",89),(20,"Reeves","5591",76);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (21,"Foreman","3464",96),(22,"Lancaster","8590",41),(23,"Cooke","5661",79),(24,"Larson","3827",99),(25,"Jarvis","9803",30),(26,"Bell","5785",33),(27,"Witt","3975",73),(28,"Burt","4992",76),(29,"Salas","5799",75),(30,"Herring","7579",68);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (31,"Fulton","3850",37),(32,"Delgado","7693",95),(33,"Simon","4953",18),(34,"Harris","8505",44),(35,"Puckett","8659",93),(36,"Dean","5612",12),(37,"Short","7469",67),(38,"Levine","1216",33),(39,"Knapp","6342",12),(40,"Sutton","5495",57);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (41,"Hancock","6687",17),(42,"Rutledge","7634",59),(43,"Bolton","8825",93),(44,"Burnett","3449",68),(45,"Wise","4325",31),(46,"Mills","9066",99),(47,"Norman","6252",91),(48,"Sheppard","7382",81),(49,"Hammond","8204",66),(50,"Moon","2041",24);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (51,"Wilkins","2202",41),(52,"Mckay","9315",29),(53,"Sykes","6884",56),(54,"Carr","5407",71),(55,"Workman","8444",80),(56,"Brock","5519",33),(57,"Morse","4207",88),(58,"Hurley","5945",27),(59,"Ellison","9565",41),(60,"Everett","8148",15);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (61,"Harper","7704",12),(62,"Hurley","8467",88),(63,"Harrington","8738",20),(64,"Sharpe","7008",61),(65,"Strickland","6536",22),(66,"Steele","5301",51),(67,"Foster","5806",91),(68,"Buchanan","6455",61),(69,"Fry","6454",100),(70,"Chavez","9845",93);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (71,"Frederick","5082",61),(72,"Becker","8152",67),(73,"Blair","9449",81),(74,"Best","4938",89),(75,"Brooks","8893",84),(76,"Estes","7011",48),(77,"Case","8245",54),(78,"Matthews","5022",21),(79,"Blackwell","4497",69),(80,"Wilkinson","8060",35);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (81,"Clements","5324",47),(82,"Robinson","1927",79),(83,"Mcfadden","6853",50),(84,"Beach","6976",76),(85,"Dillard","5317",45),(86,"Jones","2522",55),(87,"Ingram","9296",18),(88,"Estrada","7027",95),(89,"Brooks","7513",45),(90,"Donovan","9021",58);
INSERT INTO `myTable` (`id`,`login`,`passwd`,`age`) VALUES (91,"Baxter","3669",66),(92,"Petersen","1531",48),(93,"Castro","8090",23),(94,"Heath","9267",25),(95,"Raymond","6639",56),(96,"Shaw","1551",98),(97,"Sellers","4053",61),(98,"Mcfarland","9175",15),(99,"Vaughan","1806",56),(100,"West","6515",22);

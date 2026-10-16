MERGE INTO categoria (id_categoria, nombre) KEY (id_categoria) VALUES (1, 'Tecnologia'),(2, 'Clothes'),(3, 'Electrodomesticos');

MERGE INTO marca (id_marca, nombre) KEY (id_marca) VALUES (1, 'HP'),(2, 'VICTUS'),(3, 'Lenovo');

MERGE INTO unid_medida (id_unidad, nombre_medida) KEY (id_unidad) VALUES (1, 'Unidad');

MERGE INTO usuario (id_usuario, usuario, clave, estado, id_perfil) KEY (id_usuario)
    VALUES (2, 'maryory', '123456', 'ACTIVO', 1);
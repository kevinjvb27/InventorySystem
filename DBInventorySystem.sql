CREATE TABLE categorias (
	codigocategoria varchar NOT NULL PRIMARY KEY,
	categoria varchar NOT NULL,
	descripcion varchar NOT NULL,
	fechacreacion timestamp NOT NULL
);

CREATE TABLE roles (
	codigorol SERIAL PRIMARY KEY,
	rol varchar NOT NULL,
	descripcion varchar NOT NULL,
	fechacreacion timestamp NOT NULL
);

CREATE TABLE usuarios (
	nombre varchar NOT NULL,
	apellido varchar NOT NULL,
	username varchar NOT NULL PRIMARY KEY,
	password varchar NOT NULL,
	codigorol int NOT NULL,
	fechacreacion timestamp NOT NULL,
	fechamodificacion timestamp NOT NULL,
	FOREIGN KEY (codigorol) REFERENCES roles(codigorol)
);

CREATE TABLE productos (
	producto varchar NOT NULL,
	codigoproducto varchar NOT NULL PRIMARY KEY,
	descripcion varchar NULL,
	precio NUMERIC(10, 2) NOT NULL,
	cantidad int NOT NULL,
	activo boolean NOT NULL,
	codigocategoria varchar NOT NULL,
	fechacreacion timestamp NOT NULL,
	modificacion timestamp NOT NULL,
	FOREIGN KEY (codigocategoria) REFERENCES categorias(codigocategoria)
);

CREATE TABLE ordenes (
	codigoorden SERIAL PRIMARY KEY,
	fechacreacion timestamp NOT NULL,
	responsable varchar NOT NULL,
	FOREIGN KEY (responsable) REFERENCES usuarios(username)
);

CREATE TABLE detalleordenes (
	codigodetalleorden SERIAL PRIMARY KEY,
	codigoorden int NOT NULL,
	codigoproducto varchar NOT NULL,
	cantidad int NOt NULL,
	precio NUMERIC(10, 2) NOT NULL,
	FOREIGN KEY (codigoorden) REFERENCES ordenes(codigoorden),
	FOREIGN KEY (codigoproducto) REFERENCES productos(codigoproducto)
);

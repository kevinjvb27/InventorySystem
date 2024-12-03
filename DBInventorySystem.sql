--tabla producto script:
CREATE TABLE productos (
	producto varchar NOT NULL,
	codigoproducto varchar NOT NULL,
	descripcion varchar NULL,
	precio float4 NOT NULL,
	cantidad int NOT NULL,
	fechacreacion timestamp NOT NULL,
	modificacion timestamp NOT NULL,
	CONSTRAINT productos_pk PRIMARY KEY (codigoproducto),
	CONSTRAINT productos_categorias_fk FOREIGN KEY (codigocategoria) REFERENCES categorias(codigocategoria)
);

--tabla categoría de producto script:
CREATE TABLE categorias (
	categoria varchar NOT NULL,
	codigocategoria varchar NOT NULL,
	descripcion varchar NOT NULL,
	fechacreacion timestamp NOT NULL,
	CONSTRAINT categorias_pk PRIMARY KEY (codigocategoria)
);

--tabla movimientos script:

CREATE TABLE ordenes (
	tipomovimiento varchar NOT NULL,
	cantidad int NOT NULL,
	fechamovimiento timestamp NOT NULL,
	username varchar NULL,
	codigoproducto varchar NULL,
	CONSTRAINT usuario FOREIGN KEY (username) REFERENCES usuarios(username),
	CONSTRAINT codigoproducto FOREIGN KEY (codigoproducto) REFERENCES productos(codigoproducto)
);

--tabla usuario script:
CREATE TABLE usuarios (
	nombre varchar NOT NULL,
	apellido varchar NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	rol varchar NOT NULL,
	fechacreacion timestamp NOT NULL,
	fechamodificacion timestamp NOT NULL,
	CONSTRAINT usuarios_pk PRIMARY KEY (username)
);

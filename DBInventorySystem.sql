
--tabla producto script:
CREATE TABLE public.productos (
	id int NULL,
	producto varchar NOT NULL,
	codigoproducto varchar NOT NULL,
	descripcion varchar NULL,
	precio float4 NOT NULL,
	cantidad int NOT NULL,
	fechacreacion timestamp NOT NULL,
	modificacion timestamp NOT NULL,
	CONSTRAINT productos_pk PRIMARY KEY (codigoproducto),
	CONSTRAINT productos_categorias_fk FOREIGN KEY (codigocategoria) REFERENCES public.categorias(codigocategoria)
);

-- Column comments

COMMENT ON COLUMN public.productos.id IS 'id de producto';
COMMENT ON COLUMN public.productos.producto IS 'Nombre del producto';
COMMENT ON COLUMN public.productos.codigoproducto IS 'Codigo identificador unico del producto';
COMMENT ON COLUMN public.productos.descripcion IS 'Descripcion del producto';
COMMENT ON COLUMN public.productos.precio IS 'Precio del producto';
COMMENT ON COLUMN public.productos.cantidad IS 'Cantidad de stock del producto';



--tabla categoría de producto script:
CREATE TABLE public.categorias (
	id int NOT NULL,
	categoria varchar NOT NULL,
	codigocategoria varchar NOT NULL,
	descripcion varchar NOT NULL,
	fechacreacion timestamp NOT NULL,
	CONSTRAINT categorias_pk PRIMARY KEY (codigocategoria)
);

-- Column comments

COMMENT ON COLUMN public.categorias.id IS 'id de la categoria';
COMMENT ON COLUMN public.categorias.categoria IS 'Categoria de los productos';
COMMENT ON COLUMN public.categorias.codigocategoria IS 'Codigo unico para cada categoria de producto';
COMMENT ON COLUMN public.categorias.descripcion IS 'Descripcion de cada categoria';
COMMENT ON COLUMN public.categorias.fechacreacion IS 'Fecha de creacion de la categoria';


--tabla movimientos script:

CREATE TABLE public.movimientos (
	id int NOT NULL,
	tipomovimiento varchar NOT NULL,
	cantidad int NOT NULL,
	fechamovimiento timestamp NOT NULL,
	username varchar NULL,
	codigoproducto varchar NULL,
	CONSTRAINT usuario FOREIGN KEY (username) REFERENCES public.usuarios(username),
	CONSTRAINT codigoproducto FOREIGN KEY (codigoproducto) REFERENCES public.productos(codigoproducto)
);

-- Column comments

COMMENT ON COLUMN public.movimientos.id IS 'id de los movimientos de producto';
COMMENT ON COLUMN public.movimientos.tipomovimiento IS 'Muestra el tipo de movimiento entrada o salida';
COMMENT ON COLUMN public.movimientos.cantidad IS 'Muestra la cantidad añadidad o retirada';
COMMENT ON COLUMN public.movimientos.fechamovimiento IS 'Fecha de movimiento de producto entrada o salida';



--tabla usuario script:
CREATE TABLE public.usuarios (
	id int NOT NULL,
	nombre varchar NOT NULL,
	apellido varchar NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	rol varchar NOT NULL,
	fechacreacion timestamp NOT NULL,
	fechamodificacion timestamp NOT NULL,
	CONSTRAINT usuarios_pk PRIMARY KEY (username)
);

-- Column comments

COMMENT ON COLUMN public.usuarios.id IS 'id para usuarios en tabla';
COMMENT ON COLUMN public.usuarios.nombre IS 'Nombre del usuario';
COMMENT ON COLUMN public.usuarios.apellido IS 'apellido del usuario';
COMMENT ON COLUMN public.usuarios.username IS 'nombre de usuario del usuario';
COMMENT ON COLUMN public.usuarios."password" IS 'contraseña del usuario';
COMMENT ON COLUMN public.usuarios.rol IS 'rol para cada usuario';
COMMENT ON COLUMN public.usuarios.fechacreacion IS 'fecha de creacion del usuario';
COMMENT ON COLUMN public.usuarios.fechamodificacion IS 'fecha de modificacion del usuario';



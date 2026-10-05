INSERT INTO public.categoria(id_categoria, nombre)
VALUES(nextval('categoria_id_categoria_seq'::regclass), 'Artefactos');
SELECT id_categoria, nombre
FROM public.categoria;
UPDATE public.categoria
SET nombre='Comidas'
WHERE id_categoria=1;
DELETE FROM public.categoria
WHERE id_categoria=1;
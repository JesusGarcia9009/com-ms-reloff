
	CREATE TABLE public.profile (
		id serial NOT NULL,
		profile_name varchar(100) NOT NULL,
		description varchar(200) NULL,
		primary key (id)
	);


	CREATE TABLE public.users (
		id serial NOT NULL,
		national_id varchar(50) NOT NULL,
		names varchar(50) NOT NULL,
		middle_name varchar(50) NOT NULL,
		last_name varchar(50) NULL,
		mail varchar(50) NULL,
		business_position varchar(50) NULL,
		username varchar(200) NOT NULL,
		pass varchar(200) NOT NULL,
		id_profile int8 NOT NULL,
		primary key (id) 
	);

	alter table public.users add constraint fk_users_profile foreign key (id_profile)  references public.profile(id);
	
	
	INSERT INTO public.profile (id, profile_name,description) VALUES (1, 'ADMINISTRADOR','Administrador del sistema');
	INSERT INTO public.profile (id, profile_name,description) VALUES (2, 'EJECUTIVO','Ejecutivo de la compañia');
	INSERT INTO public.profile (id, profile_name,description) VALUES (3, 'VENDEDOR','Especialista de ventas de la empresa');
		
	INSERT INTO public.users (id, national_id,names,middle_name,last_name,mail,business_position,username,pass,id_profile) VALUES (1, '26873611-5','Jesús Manuel','García','Hernandez','jesus.garcia@open2000.cl','Lider Técnico','jesus.garcia','$2a$10$Ood6ka2EnrwXrd4XqF4DUeMZghaFw6va.wnO1noxkIglFCVcyUWJm',1);
	
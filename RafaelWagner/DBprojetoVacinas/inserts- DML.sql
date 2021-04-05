USE DBVACINACAO;


    /*insert pessoa*/
    
    insert into pessoa 
    (CPF 
    , NOME 
    , NASCIMENTO 
    , SEXO
    , categoria
    )
    VALUES 
    ( '48632156988'
	, 'ALBERTO'
    , '1990-02-02'
    , 'M'
    , 'PESQUISADOR'
    )
    
    ,( '58963214722'
	, 'JOANA'
    , '1985-02-02'
    , 'F'
    , 'VOLUNTARIO'
    )
   , ( '69848963211'
	, 'MARIA'
    , '1976-07-09'
    , 'F'
    , 'PUBLICO_GERAL'
    );
    
     /*inserts vacina*/
insert into vacina 
	(NOME 
    ,INICIO_PESQUISA 
    ,IDPESQUISADOR
    ,ESTAGIO
    ,nome_pais_origem
    )
    values
    ( 'gama'
    , '2020-02-05'
    , 1
    , 'APLICACAO_EM_MASSA'
    , 'Jamaica'
    )
    
    ,( 'alpha'
    , '2015-08-11'
    , 1
    , 'INICIAL'
    , 'brasil'
    );
    
    
    /*inserts aplicacoes*/
    
    INSERT INTO APLICACAO 
    (
    DT_APLICACAO
    , IDVACINA 
    , IDPESSOA 
    ) VALUES (
    '2020-03-11'
    , 1
    , 3
    )
    ,
    (
    '2018-01-11'
    , 2
    , 2
    );
    
    -- INSERTS AVALIAÇÕES
    
    INSERT INTO AVALIACAO_APLICACAO
    (
    DT_AVALIACAO
    , NOTA
    , DESCRICAO
    , IDAPLICACAO
    ) VALUES 
    (	
		'2019-02-12'
        , 4
		, 'nada a relatar, podia ser melhor pra ganhar um 5'
        , 2
    );

/*
select * from vacina;
select * from pessoa;	
select * from APLICACAO;
select * from AVALIACAO_APLICACAO;
SELECT * FROM PESQUISADOR_VACINA;
*/

select * from vacina where nome= 'GaMa';

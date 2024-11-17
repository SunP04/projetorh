insert into calculo(id, descricao)
values (1, 'Hora Extra 50%'), (2, 'Hora Extra 100%'), 
       (3, 'INSS 1.412,01 2024');

insert into hora_extra(id, percentual)
values (1, 0.5), (2, 1);

insert into inss(id, aliquota, ano_calculo, parcela_deduzir)
values (3, 0.12, 2024, 101.18);

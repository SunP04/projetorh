insert into calculo(id, descricao)
values (0, 'Hora Extra 50%'), (1, 'Hora Extra 100%'), 
       (2, 'INSS 1.412,01 2024');

insert into hora_extra(id, percentual)
values (0, 0.5), (1, 1);

insert into inss(id, aliquota, ano_calculo, parcela_deduzir)
values (2, 0.12, 2024, 101.18);

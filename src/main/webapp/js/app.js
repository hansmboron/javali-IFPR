$(function () {
    $('.money').mask('#.##0,00', { reverse: true });
    $('.money2').mask('##0', { reverse: true });
    $('.cep').mask('00000-000');
    $('.mask-siape').mask('0000000');
    $('.mask-inep').mask('00000000');
    $('.mask-cpf').mask('000.000.000-00', { reverse: true });
    $('.pis').mask('000.00000.00-0', { reverse: true });
    $('.cnpj').mask('00.000.000/0000-00', { reverse: true });
    $('.phone_with_ddd').mask('(00) 0000-0000');
    $('.uf').mask('AA');
    $('.selectonfocus').mask("00000000", { selectOnFocus: true });
    $('.placa').mask('SSS 0S00');
}) 

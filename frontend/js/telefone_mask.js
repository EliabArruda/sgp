var options = {
	onKeyPress: function(phone, e, field, options) {
		var masks = ['(00) 0000-00000', '(00) 00000-0000'];
		var mask = (phone.length > 14) ? masks[1] : masks[0];
		$('#celular').mask(mask, options);
	}
};
$('#telefone').mask('(00) 00000-0000', options);
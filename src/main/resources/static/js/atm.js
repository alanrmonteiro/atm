var atmModule = angular.module('atm', [])
	
atmModule.controller('bankController', function() {
	var bankController = this;
	
	bankController.bankName = "";
	
	bankController.saveBank = function() {
		axios.post('/bank', {"name":this.bankName})
		  .then(function(response){
		    console.log('salvo com sucesso')
		  });
	};
});

atmModule.controller('accountController', function() {
	var accountController = this;
	axios.get('/banks').then(function(response){
	    console.log('salvo com sucesso')
	});
});

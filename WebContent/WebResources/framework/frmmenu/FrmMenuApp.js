var FrmMainApp=angular.module('FrmMainApp', ['ui.tree', 'ngGrid', 'ngRoute', 'ui.bootstrap', 'ngFileUpload', 'ui.utils.masks' ]);  

FrmMainApp.config(['$routeProvider',
   	function($routeProvider) {
   	  $routeProvider.
   	    when('/FrmPerfil', {
   	      templateUrl: function(params) {
   	          return  WEB_SERVER+'FrmPerfil/';
   	      },
   	      controller: 'FrmPerfilController'
   	    }).
   	    when('/Soporte/:soporteId', {
  	      templateUrl: function(params) {
  	          return  WEB_SERVER+'FrmConsulta/Soporte/';
  	      },
  	      controller: 'SoporteController'
  	    }).	  
  	    when('/FrmTransaccion', {
   	      templateUrl: function(params) {
   	          return  WEB_SERVER+'FrmTransaccion/';
   	      },
   	      controller: 'FrmTransaccionController'
   	    }).
   	    when('/PilUsua', {
  	      templateUrl: function(params) {
  	          return  WEB_SERVER+'PilUsua/';
  	      },
  	      controller: 'PilUsuaController'
  	    }).
  	    when('/PilUsuaanalis', {
  	      templateUrl: function(params) {
  	          return  WEB_SERVER+'PilUsua/PilUsuaanalis';
  	      },
  	      controller: 'PilUsuaanalisisController'
  	    }).
  	    when('/PilSucuranalisis', {
  	      templateUrl: function(params) {
  	          return  WEB_SERVER+'PilUsua/PilSucuranalisis';
  	      },
  	      controller: 'PilUsuaanalisisController'
  	    }).
  	    when('/Planilla/:PlanillaId', {
  	      templateUrl: function(params) {
  	          return  WEB_SERVER+'FmtFormato/Planilla/';
  	      },
  	      controller: 'PlanillaController'
  	    }).	
  	    when('/CierreCarteraCuadre/:soporteId', {
  	      templateUrl: function(params) {
  	          return  WEB_SERVER+'CierreCartera/CierreCarteraCuadre/';
  	      },
  	      controller: 'CierreCarteraCuadreController'
  	    }).	
  	  when('/AdminPlanilla/:PlanillaId', {
  	      templateUrl: function(params) {
  	          return  WEB_SERVER+'FmtFormato/AdminPlanilla/';
  	      },
  	      controller: 'PlanillaController'
  	    }).	
  	  when('/CierreCancelacionesAuto/:soporteId', {
  	      templateUrl: function(params) {
  	    	return  WEB_SERVER+'CierreCancelaciones/CierreCancelacionesAutomaticas/';
  	      },
  	      controller: 'CierreCancelacionesAutomaticasController'
  	    }).
  	  when('/GeneracionCierreCartera/:soporteId', {
  	      templateUrl: function(params) {
  	    	return  WEB_SERVER+'GeneracionCierreCartera/GeneracionCierreCartera/';
  	      },
  	      controller: 'GeneracionCierreCarteraController'
  	    }). 
  	  when('/CierreGeneral/:soporteId', {
  	      templateUrl: function(params) {
  	    	return  WEB_SERVER+'CierreGeneral/CierreGeneral/';
  	      },
  	      controller: 'CierreGeneralController'
  	    }). 
   	    otherwise({
   	    	templateUrl: function(params) {
    	          return  WEB_SERVER+'FrmMenu/defaultView'; 
    	          
    	      },
   	    });
}]);
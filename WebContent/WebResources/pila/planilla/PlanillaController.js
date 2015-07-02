var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('PlanillaController', ['$scope', 'PlanillaService', '$filter', '$upload', function($scope, Service, $filter, $upload) {
	
	//Variables para los campos dinamicos
	$scope.Campos = {};
	$scope.options={};	
	$scope.paramsSend={};	
	$scope.Checkbox = {};
	$scope.Devolver = false;
	
	//botones de los formularios modal
	$scope.buttonNew=false;
	$scope.buttonEdit=false;
	
	//Evento del calendario
	$scope.open = function($event,opened) {
		
	    $event.preventDefault();
	    $event.stopPropagation();	    
	    
	    $scope[opened] = true;
	}
	
	//Funcion para inicializar los datos en la carga de la pagina
	$scope.init = function() {
		Service.getMotivos().then(function(dataResponse) {        	                                        	
			$scope.motivos=dataResponse.data.data;
			$scope.optionsMotivo=[];	
    		angular.forEach($scope.motivos, function(reg) {
    			$scope.optionsMotivo.push({value:reg.devocons, label:reg.devonomb});	
        	});
        });
		
		Service.loadI18n().then(function(dataResponse) {        	                                        	
	       	 
			Service.setI18n(dataResponse.data);
			Service.prepForLoadI18n();
        });
		
		Service.loadData().then(function(dataResponse) {  
			if(dataResponse.data.error!=undefined)
	    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	else{
	    		$scope.title=dataResponse.data.titulo;
	    		$scope.description=dataResponse.data.descri;
	    		$scope.version=dataResponse.data.version.vefocons;
	    		
	    		Service.getTbtablas('foreesta').then(function(dataResponse) {  
	    			if(dataResponse.data.error!=undefined)
	    	    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	    	else{
	    	    		$scope.tbforeesta=dataResponse.data;
	    	    		$scope.iconForeesta={};	
	    	    		angular.forEach($scope.tbforeesta, function(reg) {
	    	    			$scope.iconForeesta[reg.label]=reg.icon;	
	    	        	});
			    		//una vez cargada la version podemos cargar los campos de esa version del formato
			    		Service.getParams($scope.version).then(function(dataResponse) {  
			    	    	
			    	    	if(dataResponse.data.error!=undefined)
			    	    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
			    	    	else{
			    	    		$scope.columns=dataResponse.data.data;
			    	    		columns=[];
			    	    		columns[0]={field: "forecons", displayName: getName(Service.getI18n(), "forecons", "FMT_FORMREGI"), headerCellTemplate: filterBetweenNumber, visible: false};
			    	    		columns[1]={field: "forefech", displayName: getName(Service.getI18n(), "forefech", "FMT_FORMREGI"), headerCellTemplate: filterBetweenDate};
			    	    		columns[2]={field: "foreesta", displayName: getName(Service.getI18n(), "foreesta", "FMT_FORMREGI"), visible: false};
			    	    		columns[3]={field: "tablvast", displayName: getName(Service.getI18n(), "foreesta", "FMT_FORMREGI"), headerCellTemplate: filterText, cellTemplate: '<div><img src="{{icons[row.getProperty(col.field)]}}" width="20" height="20"></img>{{row.getProperty(col.field)}}</div>'};
			    	    		columns[4]={field: "usuaunit", displayName: getName(Service.getI18n(), "usuaunit", "PIL_USUA"), headerCellTemplate: filterText, visible: false};
			    	    		columns[5]={field: "usuarazo", displayName: getName(Service.getI18n(), "usuarazo", "PIL_USUA"), headerCellTemplate: filterText, visible: false};
			    	    		columns[6]={field: "usuasucu", displayName: getName(Service.getI18n(), "usuasucu", "PIL_USUA"), headerCellTemplate: filterBetweenNumber, visible: false};
			    	    		//recorro los campos para cargar los data de los combos
			    	    		for(i=0; i<$scope.columns.length;i++){    				    	    	    		
	    	    	    			//si el tipo de dato es columna
	    	    	    			if($scope.columns[i].camptipo=='CS' || $scope.columns[i].camptipo=='CI'){    				    				    				
	    	    	    				//se pasa el codigo del combo
	    	    	    				Service.getCombo($scope.columns[i].campcomb).then(function(dataResponse) {    					    					
	    	    	    					if(dataResponse.data.error!=undefined)
	    	    	    			    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	    	    			    	else{    			   
	    	    	    			    		//se carga la data en los options
	    	    	    			    		$scope.options[dataResponse.data.combo] = dataResponse.data.data;      			    		
	    	    	    			    	}
	    	    	    				});    				    				 		    				    		
	    	    	    			}
	    	    	    			
	    	    	    			if($scope.columns[i].camptipo=="D" || $scope.columns[i].camptipo=="T")
	    	    	    				columns[i+7]={field: $scope.columns[i].campnomb, displayName: $scope.columns[i].camplabe, headerCellTemplate: filterBetweenDate};
	    	    	    			else if($scope.columns[i].camptipo=="O" || $scope.columns[i].camptipo=="I" || $scope.columns[i].camptipo=="L" || $scope.columns[i].camptipo=="F")
	    	    	    				columns[i+7]={field: $scope.columns[i].campnomb, displayName: $scope.columns[i].camplabe, headerCellTemplate: filterBetweenNumber, cellTemplate:'<div>{{row.getProperty(col.field) | number}}</div>'};
	    	    	    			else if($scope.columns[i].camptipo=="B")
	    	    	    				columns[i+7]={field: $scope.columns[i].campnomb, displayName: $scope.columns[i].camplabe, headerCellTemplate: filterBetweenNumber};
	    	    	    			else
	    	    	    				columns[i+7]={field: $scope.columns[i].campnomb, displayName: $scope.columns[i].camplabe, headerCellTemplate: filterText};
	    	    	    		}
			    	    		
			    	    		$scope.usuaunitlabel=getName(Service.getI18n(), "usuaunit", "PIL_USUA");
			    	    		$scope.usuarazolabel=getName(Service.getI18n(), "usuarazo", "PIL_USUA");
			    	    		$scope.devonomblabel=getName(Service.getI18n(), "devonomb", "PIL_MOTIVO");
			    	    		$scope.devodesclabel=getName(Service.getI18n(), "devodesc", "PIL_MOTIVO");
			    	    		$scope.devotitleabel=getName(Service.getI18n(), "-", "PIL_MOTIFORM");
			    	    		$scope.columnDefs=columns;	
			    	    		
			    	    		//cargo las sucursales a las cuales tiene asigando este analista
			    	    		Service.getSucursales().then(function(dataResponse) { 
			    	    			$scope.sucursales=dataResponse.data.data;
			    	    			$scope.basicSearchQuery=[{campo: 'forevefo', tipo: "=", val1: $scope.version, tipodato: "Number"} , {campo: 'usuasucu', tipo: "IN", val1: ""+$scope.sucursales+"", tipodato: "Number"}];
			    	    			$scope.directiveGrid=true;
			    	    		});	    	    		
			    	    	}		    	
			    	    });
	    	    	 }
	    		   });	    	
	    	}
		});
		
	}
	
    $scope.gridOptions = {  
    	sortInfo:{ fields: ['forecons'], directions: ['desc']},
    	selectedItems: [],
        afterSelectionChange: function (rowItem, event) {
        	for(i=0; i<$scope.columns.length;i++){            		
        		if($scope.columns[i].camptipo=='O' || $scope.columns[i].camptipo=='F')
        			$scope.Campos[$scope.columns[i].campnomb]=parseFloat(rowItem.entity[$scope.columns[i].campnomb].toString(),20);
        		else
        			$scope.Campos[$scope.columns[i].campnomb]=rowItem.entity[$scope.columns[i].campnomb];
        		
        	}
        	$scope.Campos["forecons"]=rowItem.entity["forecons"];
        	$scope.Campos["foreesta"]=rowItem.entity["foreesta"];
        	$scope.Campos["usuaunit"]=rowItem.entity["usuaunit"];
        	$scope.Campos["usuasucu"]=rowItem.entity["usuasucu"];
        	$scope.Campos["usuarazo"]=rowItem.entity["usuarazo"];
        
        	Service.prepForLoad(rowItem.entity.forecons);      
        }
    };
     
    function getName(i18n,colum,modulo){
    	var log = [];
    	angular.forEach(i18n, function(fila, index) {
    		if(fila.etincamp==colum && fila.modunomb==modulo)  
    			this.push(fila);
   		}, log);
    	
    	return log[0].etinetiq;        	
    }
    
    $scope.whatClassIsIt= function(column){
    	var log = [];
    	
    	angular.forEach($scope.columnDefs, function(fila, index) {
    		if(fila.field==column)
    			this.push(fila);
   		}, log);
    	
    	if(log[0]!=null)
    		return log[0].displayName;
    	else 
    		return "";
    } 
    
   //Funciones de las CRUD                     
	$scope.aprobarRecord= function(){	
		var verify=true;
		
		if(!$scope.gridOptions.selectedItems.length>0)
			alert("Favor seleccione una fila");		
		else{			    					
			for(i=0; i<$scope.columns.length;i++){
				//Verificar si los datos requeridos cumplen con haber sido digitados
				if($scope.columns[i].campvali==1){					
					if($scope.Checkbox[$scope.columns[i].campnomb]!=true){
						verify=false;
						break;
					}								
				}
			}
			
			if($scope.Checkbox['usuarazo']!=true || $scope.Checkbox['usuaunit']!=true)
				verify=false;
							
			if(verify){		
				
				Service.aprobarRecord($scope.Campos["forecons"]).then(function(dataResponse) {					 	      
	        		alert(dataResponse.data);	        				        			
		        	
	        		$scope.loadMyGrid();
		        });
			}else{ 
				alert("Datos vacios o incorrectos: Favor diligencie todos los campos");
			}
		}
    }
		
	$scope.$on('gridEvento', function(event, pageSize, currentPage, order, searchQuery) {   
		$scope.pageSize=pageSize;
		$scope.currentPage=currentPage;
		$scope.order=order;
		$scope.searchQuery=searchQuery;
		
    	if($scope.directiveGrid)
    		$scope.loadMyGrid();
    });
	
	$scope.loadMyGrid= function(){	
		
		Service.getData($scope.columns, $scope.version, $scope.pageSize, $scope.currentPage, $scope.order, $scope.searchQuery.concat($scope.basicSearchQuery)).then(function(dataResponse) {
    		if(dataResponse.data.error!=undefined)
    			alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
        	else 
        		$scope.$broadcast('loadDataGrid',dataResponse.data.data, dataResponse.data.count, $scope.pageSize, $scope.currentPage);
        });
	}
	
	$scope.putDesc = function() {
		     	                                        	
		angular.forEach($scope.motivos, function(reg) {
			if(reg.devocons===$scope.Campos['devonomb'])
				$scope.Campos['devodesc']="Sr(a) "+$scope.Campos['usuarazo']+". \nLa planilla diligenciada ha sido devuelta por el siguiente motivo:\n "+reg.devodesc;	
    	});
	}
	
	$scope.devolverActivar = function() {
     	
		$scope.Devolver = true;
	}
	
	$scope.devolverRecord= function(){	
				
		Service.devolverRecord($scope.Campos["forecons"],$scope.Campos["devonomb"],$scope.Campos["devodesc"]).then(function(dataResponse) {		
			if(dataResponse.data.error!=undefined)
    			alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
        	else{
        		alert(dataResponse.data.data);
        		$scope.Devolver = false;
        	}
        	
    		$scope.loadMyGrid();
        });
    }
	
	$scope.gestionarPlanilla = function() {
		
	    if($scope.Campos["foreesta"]==='A' || $scope.Campos["foreesta"]==='C')
	    	$scope.activeButtons=false;
	    else
	    	$scope.activeButtons=true;
	}
 }            
])
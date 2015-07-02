var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('PilUsuasucuranalisisController', ['$scope', 'PilSucuranalisisService',function($scope, Service) {
    	//botones de los formularios modal
    	$scope.buttonNew=false;
    	$scope.buttonEdit=false;
    	$scope.buttonDelete=false;    	    	                          
              
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
        	
        	if(log[0]!=undefined)
        		return log[0].displayName;
        	else
        		return "";    
        }                  
               
    	$scope.createRecordForm= function(){
    	    $scope.buttonNew=true;
    		$scope.buttonEdit=false;
    		$scope.buttonDelete=false;
    		
    		$scope.ussucons = undefined ,
    		 $scope.ussuusua = "" ,
    		 $scope.ussusucu = $scope.ussusucu ,
    		 $scope.ussuesta = "A"         	
        }                
        
    	$scope.loadDatatoForm= function(){			
    		
    		if($scope.ussucons!=null || $scope.ussucons!=undefined){
    			$scope.buttonNew=false;
    			$scope.buttonEdit=true;
    			$scope.buttonDelete=false;					        	
    		}
    		else
    			alert("Favor seleccione una fila");
        }                       
    	
    	$scope.deleteRecordForm= function(){
    		
    		if($scope.ussucons!=null || $scope.ussucons!=undefined){
    			$scope.buttonNew=false;
    			$scope.buttonEdit=false;
    			$scope.buttonDelete=true;					        	
    		}
    		else
    			alert("Favor seleccione una fila");
        }
    	
    	$scope.insertRecord= function(){
    		
    		Service.insertRecordChild($scope.ussucons,$scope.ussuusua,$scope.ussusucu,$scope.ussuesta).then(function(dataResponse) {        	            
    			row=dataResponse.data;
    			
    			 $scope.ussucons = row.ussucons;
    			 $scope.ussuusua = row.ussuusua;
    			 $scope.ussusucu = row.ussusucu;
    			 $scope.ussuesta = row.ussuesta;    
    							
            	alert("Se creo el registro correctamente");
            	
            	$scope.loadMyGrid();
            }); 
        }
    	
    	$scope.updateRecord= function(){
    		
    		Service.updateRecordChild($scope.ussucons,$scope.ussuusua,$scope.ussusucu,$scope.ussuesta).then(function(dataResponse) {        	            
    			row=dataResponse.data;
    			
    			 $scope.ussucons = row.ussucons;
    			 $scope.ussuusua = row.ussuusua;
    			 $scope.ussusucu = row.ussusucu;
    			 $scope.ussuesta = row.ussuesta; 
    								        
            	alert("Se actualizo el registro correctamente");
            	
            	$scope.loadMyGrid();
            }); 
        }
    	
    	$scope.deleteRecord= function(){
    					
    		Service.deleteRecordChild($scope.ussucons,$scope.ussuusua,$scope.ussusucu,$scope.ussuesta).then(function(dataResponse) {        	            
    			row=dataResponse.data;
    			
    			 $scope.ussucons = row.ussucons;
    			 $scope.ussuusua = row.ussuusua;
    			 $scope.ussusucu = row.ussusucu;
    			 $scope.ussuesta = row.ussuesta; 

            	alert("Se borro el registro correctamente");
            	
            	$scope.loadMyGrid();
            }); 
        }
		
		$scope.$on('handleBroadcastUsuasucur', function() {
			$scope.ussusucu = Service.id;
			
			$scope.loadMyGrid();        
	    });

		$scope.$on('handleBroadcastUsuasucurI18n', function() {
			
			loadI18n();	 
	    }); 

		//funcion del inicializar componentes
		function loadI18n() {
			                                       	
			columns=[ 	{ field: "ussucons", displayName: getName(Service.getI18n(), "ussucons",  "PIL_USUSUCU"), visible: false, headerCellTemplate: filterBetweenNumber } ,
	    	          	{ field: "ussuusua", displayName: getName(Service.getI18n(), "ussuusua",  "PIL_USUSUCU"), visible: false, headerCellTemplate: filterBetweenNumber } ,
	    	          	{ field: "ussusucu", displayName: getName(Service.getI18n(), "ussusucu",  "PIL_USUSUCU"), visible: false, headerCellTemplate: filterBetweenNumber } ,
	    	          	{ field: "usuausua", displayName: getName(Service.getI18n(), "usuausua",  "PIL_USUA"), visible: true, headerCellTemplate: filterText },  
	    	          	{ field: "ussuesta", displayName: getName(Service.getI18n(), "ussuesta",  "PIL_USUSUCU"), visible: true, headerCellTemplate: filterText }  
	             ];
            
            $scope.columnDefs=columns;
            
            $scope.ventanaTitulo=getName(Service.getI18n(), "-", "PIL_USUSUCU");
            
            $scope.gridOptions = {  
            	sortInfo:{ fields: ['ussusucu'], directions: ['desc']},
            	selectedItems: [],
                afterSelectionChange: function (rowItem, event) {
                	$scope.ussucons = rowItem.entity.ussucons; 
       	           	 $scope.ussuusua = rowItem.entity.ussuusua; 
       	           	 $scope.ussusucu = rowItem.entity.ussusucu; 
       	           	 $scope.ussuesta = rowItem.entity.ussuesta;  
                }
            };
            
            $scope.basicSearchQuery=[{campo: 'usuatipo', tipo: "=", val1: 2, tipodato: "Number"} ];
            	            
            $scope.directiveGrid=true;
	              									
			Service.getCombo("usuaesta").then(function(dataResponse) {  
				if(dataResponse.data.error!=undefined)
		    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{				
					$scope.optionsEstados = dataResponse.data;
				}
	        });

			Service.getConsulta(31).then(function(dataResponse) {    					    					
				if(dataResponse.data.error!=undefined)
		    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{    			   
		    		$scope.optionsAnalistas = dataResponse.data.data;   
		    	}
			});
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
			var basicSearchQuery;
			
			if($scope.ussusucu!=null){
				basicSearchQuery=[{campo: 'ussusucu', tipo: "=", val1: $scope.ussusucu, tipodato: "Number"}];
				
				if($scope.searchQuery!=undefined)
					basicSearchQuery=$scope.searchQuery.concat(basicSearchQuery);	
				
				Service.getDataChild($scope.pageSize, $scope.currentPage, $scope.order, basicSearchQuery).then(function(dataResponse) {
		    		if(dataResponse.data.error!=undefined)
		    			alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		        	else 
		        		$scope.$broadcast('loadDataGrid',dataResponse.data.data, dataResponse.data.count, $scope.pageSize, $scope.currentPage);
		        });
			}
		}
				
    }            
    ])
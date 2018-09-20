/**
 * NotificationService
 */
app.factory('NotificationService',function($http){
	var notificationService={}
	var BASE_URL="http://localhost:8085/project2middleware"
	notificationService.getNotification=function(id){
		return $http.get(BASE_URL +"/notification/"+id)
	}	
	notificationService.getNotificationNotViewed=function()
	{
		return $http.get(BASE_URL + "/notifications")
	}
	notificationService.updateNotification=function(id){
		return $http.put(BASE_URL + "/updatenotification/"+id)
	}
	return notificationService;
})
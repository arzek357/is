angular.module('app').controller('registerController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/system';
    $scope.status={success : ""};
    $scope.tryToRegister = function () {
        $http.post(contextPath + '/registration', $scope.user)

            .then(function successCallback(response) {
                $scope.status={success : "success"};

            }, function errorCallback(response) {
                $scope.status={success : "failed"};
            });
    };

});
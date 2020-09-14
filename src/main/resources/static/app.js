var app = angular.module('calcApp', []);
app.controller('calcCtrl', function ($scope, $http) {
    $scope.sports = [];
    $scope.requestData = {};
    $scope.calculated = {};

    $scope.load = function () {
        $http({
            method: 'GET',
            url: '/api/v1/sport/list'
        })
            .then(function (data) {
                $scope.sports = data.data;
            })
            .catch(reason => console.log(reason));
    };

    $scope.calculate = function () {
        $http({
            method: 'GET',
            url: '/api/v1/sport/' + $scope.requestData.sport + '/result/' + $scope.requestData.result,
        })
            .then(function (data) {
                $scope.calculated = data.data;
            })
            .catch(reason => console.log(reason));
    };
})

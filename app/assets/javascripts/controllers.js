/** Controllers */
angular.module('log.controllers', [])
    .filter('logLevel', function() {
        return function(logs, logLevel) {
            var filteredLogs = [];
            for (var i=0; i < logs.length; i++){
                if (logs[i].level == logLevel) {
                    filteredLogs.push(logs[i]);
                }
            }

            return filteredLogs;
        }
    })

    .controller('LogsCtrl', function($scope) {
        $scope.logs = []

        function handleEvent(event) {
            var json = JSON.parse(event.data)

            if(json.type == "loghistory") {
                json.history.forEach(function(logMessage) {
                    $scope.logs.push(logMessage)
                })
            }
            else if(json.type == "logupdate") {
                $scope.logs.shift()
                $scope.logs.push(json)
            }

            $scope.$apply()
        }

        //var wsUrl = $("body").data("ws-url")
        //var logStream = new WebSocket(wsUrl)
        //
        //logStream.onmessage = function(event) {
        //    handleEvent(event)
        //}
    })

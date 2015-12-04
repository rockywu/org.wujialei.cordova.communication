/*
 * 添加Fx Tools 版本
 *
 * @author rockywu
 * @email rockywu@anjuke.com
 */
var exec = require("cordova/exec");

function FxTools() {

}
FxTools.prototype.deviceRegister = function(userId, cityId, successCallback, errorCallback) {
    exec(successCallback, errorCallback, "FxTools", "deviceRegister", [userId, cityId]);
}
FxTools.prototype.networkReload = function(bool, successCallback, errorCallback) {
    exec(successCallback, errorCallback, "FxTools", "networkReload", [bool]);
}
FxTools.prototype.displayMessage = function(message, successCallback, errorCallback) {
    exec(successCallback, errorCallback, "FxTools", "displayMessage", [message]);
}
module.exports = new FxTools();

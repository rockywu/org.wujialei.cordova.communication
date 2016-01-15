/*
 * 添加Fx Tools 版本
 *
 * @author rockywu
 * @email rockywu@anjuke.com
 */
var exec = require("cordova/exec");

function Communication() {}
Communication.prototype.ajax = function(config) {
    exec(config.success || function() {},
        config.error || function() {},
        "Communication",
        "ajax",
        [
            config.url || window.location.href,
            config.type || 'GET',
            config.dataType || "json",
            config.data || {}
        ]
    );
}
module.exports = new Communication();


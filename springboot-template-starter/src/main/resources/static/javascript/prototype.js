/**
 * Created by ANdady on 2019/8/9.
 */

/**
 * 获取元素所在数组的下标
 * @param obj
 * @returns {number}
 */
Array.prototype.indexOf = function (obj) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] === obj) {
            return i;
        }
    }
    return -1;
};
/**
 * 上传数组对应的元素
 * @param obj
 */
Array.prototype.remove = function (obj) {
    var index = this.indexOf(obj);
    if (index > -1) {
        this.splice(index, 1);
    }
};
/**
 * 数字转货币
 * @param places 保留的小数点位数
 * @param symbol 货币符号
 * @param thousand 千位数分割符号
 * @param decimal 货币
 * @returns {string}
 */
Number.prototype.formatMoney = function (places, symbol, thousand, decimal) {
    places = !isNaN(places = Math.abs(places)) ? places : 2;
    symbol = symbol !== undefined ? symbol : "$";
    thousand = thousand || ",";
    decimal = decimal || ".";
    var number = this,
        negative = number < 0 ? "-" : "",
        i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
};

/**
 * 将表达元素转换成JSON数据
 * @returns {{}}
 */
$.fn.serializeJson = function () {
    var serializeObj = {};
    var array = this.serializeArray();
    var str = this.serialize();
    $(array).each(function () {
        if (serializeObj[this.name]) {
            if ($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            } else {
                serializeObj[this.name] = [serializeObj[this.name], this.value];
            }
        } else {
            serializeObj[this.name] = this.value;
        }
    });
    return serializeObj;
};
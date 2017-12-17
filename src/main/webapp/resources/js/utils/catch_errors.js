(function(w){
    w.onerror = function(errorMessage, file, line, col, error){
        _sendError({
            message: error.message || 'Unhandled error',
            line: line,
            file: file
        });
        return true;
    };

    navigator.sayswho = (function(){
        var agent = navigator.userAgent,
            tem,
            matches = agent.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];

        if (/trident/i.test(matches[1])) {
            tem = /\brv[ :]+(\d+)/g.exec(agent) || [];
            return 'IE '+(tem[1] || '');
        }

        if (matches[1] === 'Chrome') {
            tem = agent.match(/\b(OPR|Edge)\/(\d+)/);
            if (tem)
                return tem.slice(1).join(' ').replace('OPR', 'Opera');
        }

        matches = matches[2] ? [matches[1], matches[2]] : [navigator.appName, navigator.appVersion, '-?'];

        tem = agent.match(/version\/(\d+)/i);
        if (tem)
            matches.splice(1, 1, tem[1]);
        return matches.join(' ');
    })();

    navigator.os = (function(){
        var os_name = "Unknown";
        if (window.navigator.userAgent.indexOf("Windows NT 10.0") > -1) os_name="Windows 10";
        else if (window.navigator.userAgent.indexOf("Windows NT 6.2") > -1) os_name="Windows 8";
        else if (window.navigator.userAgent.indexOf("Windows NT 6.1") > -1) os_name="Windows 7";
        else if (window.navigator.userAgent.indexOf("Windows NT 6.0") > -1) os_name="Windows Vista";
        else if (window.navigator.userAgent.indexOf("Windows NT 5.1") > -1) os_name="Windows XP";
        else if (window.navigator.userAgent.indexOf("Windows NT 5.0") > -1) os_name="Windows 2000";
        else if (window.navigator.userAgent.indexOf("Mac")            > -1) os_name="Mac/iOS";
        else if (window.navigator.userAgent.indexOf("X11")            > -1) os_name="UNIX";
        else if (window.navigator.userAgent.indexOf("Linux")          > -1) os_name="Linux";

        return os_name;
    })();

    function _sendError(object){
        object.file = object.file.replace(location.origin, '');

        var xhr = new XMLHttpRequest(),
            toSend = 'payload='+JSON.stringify({
                    username: 'Error logger',
                    icon_emoji: ':rage:',
                    channel: '#errors',
                    attachments: [{
                        fallback: "Error on frontend",
                        color: "#9C1A22",
                        pretext: "Error on frontend",
                        fields: [{
                            title: 'at page',
                            value: '_<'+location.href+'|'+document.title+'>_',
                            short: true
                        }, {
                            title: 'browser',
                            value: navigator.sayswho,
                            short: true
                        }, {
                            title: 'OS',
                            value: navigator.os,
                            short: true
                        }, {
                            title: 'error description',
                            value: 'error [' + object.message + '] in [' + object.file + '] at [' + object.line + ']'
                        }],
                        mrkdwn_in: ['fields']
                    }]
                });
        xhr.open('POST', 'https://hooks.slack.com/services/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', false);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(toSend);
    }
})(window);
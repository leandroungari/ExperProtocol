class Request {

    constructor(url) {

        this.setTarget(url);
    }

    send(data, success = () => {}, error = () => {}) {

        $.ajax({
            url: this.url,
            type: "post",
            async: true,
            data: JSON.stringify(data),
            cache: false
        })
        .done(data => success(data))
        .fail(() => error());

    }

    setTarget(url) {

        this.url = url;
    }
}
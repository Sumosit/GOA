<style>
    @import "https://cdn.jsdelivr.net/npm/animate.css@3.5.1";

    body {
        background: rgb(233, 233, 233);
        font-family:Arial, Helvetica, sans-serif;
    }
    html, body {
        height: calc(100% - 50px);
    }

    #app {
        background: #fff;
        width: 50%;
        padding: 30px;
        border-radius: 10px;
        margin: 50px auto 0 auto;
        height: calc(60% - 50px);
    }

    .page {
        position: fixed;
        width: inherit;
    }
    .router-anim-enter-active {
        animation: coming 1s;
        animation-delay: .5s;
        opacity: 0;
    }
    .router-anim-leave-active {
        animation: going 1s;
    }

    @keyframes going {
        from {
            transform: translateX(0);
        }
        to {
            transform: translateX(-50px);
            opacity: 0;
        }
    }
    @keyframes coming {
        from {
            transform: translateX(-50px);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
</style>

<transition name="router-anim">
    <router-view/>
</transition>
package cz.cvut.fel.pjv.bukovja4.engine.logic.controls;

import java.util.function.Function;

import org.lwjgl.system.Callback;
// import org.lwjgl.system.CallbackI;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public abstract class BaseEvent {
    public Function<Object[], ?> callback;
    public long windowHandle;

    public abstract void register();
    public Callback handler;

    public void init(Function<Object[], ?> callback) {
        this.callback = callback;
        LOG.warn("Initializing event: " + this.getClass().getSimpleName());
    }
 
    public void unRegister() {
        if (this.handler != null) {
            this.handler.free();
            this.handler = null;
        }
        this.callback = null;
        LOG.warn("Unregistering event: " + this.getClass().getSimpleName());
    }
}

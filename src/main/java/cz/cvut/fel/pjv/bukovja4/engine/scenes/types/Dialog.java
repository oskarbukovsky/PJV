package cz.cvut.fel.pjv.bukovja4.engine.scenes.types;

import cz.cvut.fel.pjv.bukovja4.engine.scenes.BaseScene;

/**
 * Dialog/popup scene implementation that loads from YAML files.
 * Extends {@link BaseScene} to provide dialog-specific functionality.
 */
public class Dialog extends BaseScene {
    /**
     * Creates a dialog with the specified name
     * 
     * @param name The dialog name used to locate the dialog YAML file
     * @throws Throwable if dialog loading fails
     * @see BaseScene#Load(String)
     */
    public Dialog(String name) throws Throwable {
        super.Load("dialog/" + name);
    }

    /**
     * Updates the dialog state for the current frame
     * {@inheritDoc}
     */
    @Override
    public void Tick() {
    }
}

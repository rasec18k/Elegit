package edugit.treefx;

import javafx.scene.Group;

/**
 * Created by makik on 6/10/15.
 *
 * Connects two cells in the TreeGraph using a DirectedLine
 */
public class Edge extends Group {

    protected Cell source;
    protected Cell target;

    DirectedLine line;

    /**
     * Constructs a directed line between the source and target cells and binds
     * properties to handle relocation smoothly
     * @param source the source (parent) cell
     * @param target the target (child) cell
     */
    public Edge(Cell source, Cell target) {

        this.source = source;
        this.target = target;

        line = new DirectedLine();

        line.startXProperty().bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind(source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));

        line.endXProperty().bind(target.layoutXProperty().add(target.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind(target.layoutYProperty().add(target.getBoundsInParent().getHeight() / 2.0));

        getChildren().add(line);

    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}
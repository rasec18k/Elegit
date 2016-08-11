package elegit;

import javafx.scene.control.Tooltip;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A subclass of RepoFile that contains a file that Git is ignoring.
 */
public class IgnoredRepoFile extends RepoFile {

    public IgnoredRepoFile(Path filePath, RepoHelper repo) {
        super(filePath, repo);
        diffButton.setText("IGNORED");
        diffButton.setId("ignoredDiffButton");
        Tooltip tooltip = new Tooltip("This file is being ignored because it's in your .gitignore.\nRemove it from your .gitignore if you ant to add it to git");
        tooltip.setFont(new javafx.scene.text.Font(12));
        diffButton.setTooltip(tooltip);
    }

    public IgnoredRepoFile(String filePathString, RepoHelper repo) {
        this(Paths.get(filePathString), repo);
    }

    @Override public boolean canAdd() { return false; }

    @Override public boolean canRemove() { return false; }
}
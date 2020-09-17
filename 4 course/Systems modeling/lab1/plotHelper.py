import matplotlib.pyplot as plt

def scatterplot(x_data, y_data, x_label="", y_label="", title="", color="b", yscale_log=False):
    _, ax = plt.subplots()

    ax.scatter(x_data, y_data, s=5, color=color, alpha=0.75)

    if yscale_log == True:
        ax.set_yscale('log')

    ax.set_title(title)
    ax.set_xlabel(x_label)
    ax.set_ylabel(y_label)
    plt.show()
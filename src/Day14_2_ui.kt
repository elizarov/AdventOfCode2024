import java.awt.*
import javax.swing.*

fun main() {
    val input = readInput("Day14")
    val m = 101
    val n = 103
    data class RD(val x: Int, val y: Int, val vx: Int, val vy: Int)
    val rd = input.map { s ->
        val (x, y, vx, vy) = Regex("p=(\\d+),(\\d+) v=(\\-?[0-9]+),(\\-?[0-9]+)").matchEntire(s)!!.destructured.toList()
            .map { it.toInt() }
        RD(x, y, vx, vy)
    }
    val c = Array(n) { IntArray(m) }
    var p: List<P2> = emptyList()
    val dim = 5
    val label = JLabel("Start")
    val canvas = object : JPanel() {
        init {
            preferredSize = Dimension(dim * m, dim * n)
        }

        override fun paintComponent(g: Graphics) {
            g.color = Color.WHITE
            g.fillRect(0, 0, dim * m, dim * n)
            g.color = Color.BLACK
            for (x0 in 0..<m) for (y0 in 0..<n) if (c[y0][x0] > 0) {
                val x = x0 * dim
                val y = y0 * dim
                g.fillRect(x, y, dim, dim)
            }
        }
    }
    var k = 0
    fun update() {
        for ((x, y) in p) {
            c[y][x] = 0
        }
        p = rd.map { (x, y, vx, vy) ->
            P2((x + vx * k).mod(m), (y + vy * k).mod(n))
        }
        for ((x, y) in p) {
            c[y][x]++
        }
        label.text = "Step $k"
        canvas.repaint()
    }

    val nextButton = JButton("+1").apply {
        addActionListener {
            k++
            update()
        }
    }
    val next101Button = JButton("+101").apply {
        addActionListener {
            k += 101
            update()
        }
    }
    val prevButton = JButton("-1").apply {
        addActionListener {
            k--
            update()
        }
    }
    JFrame("Day12_2").apply {
        contentPane = JPanel(BorderLayout())
        contentPane.add(canvas, BorderLayout.CENTER)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        contentPane.add(canvas, BorderLayout.CENTER)
        val bottomPanel = JPanel(FlowLayout(FlowLayout.CENTER)).apply {
            add(label)
            add(prevButton)
            add(nextButton)
            add(next101Button)
        }
        contentPane.add(bottomPanel, BorderLayout.SOUTH)
        pack()
        isVisible = true
    }
}

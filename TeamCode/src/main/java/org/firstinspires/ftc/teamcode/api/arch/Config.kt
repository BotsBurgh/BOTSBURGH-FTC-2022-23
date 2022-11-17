package org.firstinspires.ftc.teamcode.api.arch

data class Config(
    val mode: RobotMode = RobotMode.Autonomous,
    val autoStrategy: AutoStrategy = AutoStrategy(ArenaSide.TopBlue) // Consider different default?
)

enum class RobotMode {
    TeleOp,
    Autonomous,
}

data class AutoStrategy(val side: ArenaSide)

enum class ArenaSide {
    TopBlue {
        override fun isBlue() = true
        override fun isRed() = false
        override fun isTop() = true
        override fun isBottom() = false
    },
    BottomBlue {
        override fun isBlue() = true
        override fun isRed() = false
        override fun isTop() = false
        override fun isBottom() = true
    },
    TopRed {
        override fun isBlue() = false
        override fun isRed() = true
        override fun isTop() = true
        override fun isBottom() = false
    },
    BottomRed {
        override fun isBlue() = false
        override fun isRed() = true
        override fun isTop() = false
        override fun isBottom() = true
    };

    abstract fun isBlue(): Boolean
    abstract fun isRed(): Boolean
    abstract fun isTop(): Boolean
    abstract fun isBottom(): Boolean
}

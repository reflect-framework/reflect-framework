package nth.reflect.fw.vaadin.css;

public enum SizeUnit {

	PERCENT(false) {

		@Override
		public String toString() {
			return "%";
		}

	},
	IN(true) {

		@Override
		public String toString() {
			return "in";
		}

	},
	CM(true) {

		@Override
		public String toString() {
			return "cm";
		}

	},
	MM(true) {

		@Override
		public String toString() {
			return "mm";
		}

	},
	EM(false) {

		@Override
		public String toString() {
			return "em";
		}

	},
	EX(false) {

		@Override
		public String toString() {
			return "ex";
		}

	},
	PT(true) {
		@Override
		public String toString() {
			return "pt";
		}

	},
	PC(true) {
		@Override
		public String toString() {
			return "pc";
		}

	},
	PX(true) {
		@Override
		public String toString() {
			return "px";
		}

	},

	DEG(true) {
		@Override
		public String toString() {
			return "deg";
		}

	},

	GRAD(true) {

		@Override
		public String toString() {
			return "grad";
		}

	},

	RAD(true) {

		@Override
		public String toString() {
			return "rad";
		}

	},

	TURN(true) {

		@Override
		public String toString() {
			return "turn";
		}

	},

	S(true) {

		@Override
		public String toString() {
			return "s";
		}

	},

	MS(true) {

		@Override
		public String toString() {
			return "ms";
		}

	};

	private SizeUnit(boolean absolute) {
		this.absolute = absolute;
	}

	private final boolean absolute;

	public boolean isAbsolute() {
		return absolute;
	}

	// RT-14711: The spec says 1px is equal to 0.75pt
	// 72 / 0.75 = 96
	static final private double DOTS_PER_INCH = 96.0;
	static final private double POINTS_PER_INCH = 72.0;
	static final private double CM_PER_INCH = 2.54;
	static final private double MM_PER_INCH = CM_PER_INCH * 10;
	static final private double POINTS_PER_PICA = 12.0;

	public String asString(int size) {
		StringBuilder reply = new StringBuilder();
		reply.append(size);
		reply.append(this.toString());
		return reply.toString();
	}

}

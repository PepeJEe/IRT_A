import java.util.Random;

enum Weather {
    CLOUDY(40f, 1f, 0.3f, 0.45f),
    CLEAR(60f, 4f, 0f, 0.50f),
    RAINY(80f, 8f, 0.8f, 0.57f);

    public float windSpeedSky;
    public float windSpeedGround;
    public float cloudCoverage;
    public float cloudNumCon;

    private Weather(float windSpeedSky, float windSpeedGround, float cloudCoverage, float cloudNumCon) {
        this.windSpeedSky = windSpeedSky;
        this.windSpeedGround = windSpeedGround;
        this.cloudCoverage = cloudCoverage;
        this.cloudNumCon = cloudNumCon;
    }

    public float GetWindSpeedSky() {
        return windSpeedSky;
    }

    public float GetWindSpeedGround() {
        return windSpeedGround;
    }

    public float GetCloudCoverage() {
        return cloudCoverage;
    }

    public float GetCloudNumCon() {
        return cloudNumCon;
    }

    private static final Random RANDOM = new Random();

    public static Weather GetRandomWeather() {
        Weather[] values = Weather.values();
        return values[RANDOM.nextInt(values.length)];
    }
}

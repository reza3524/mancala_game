package ir.hamrahcard.mancala.service.impl.factory;

import ir.hamrahcard.mancala.base.BaseDto;
import ir.hamrahcard.mancala.base.BaseEntity;
import ir.hamrahcard.mancala.base.BaseTransfer;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class AbstractFactory<
        E extends BaseEntity<? extends Serializable>,
        D extends BaseDto<? extends Serializable>,
        T extends BaseTransfer<? extends Serializable>> {

    protected static final Faker FAKER = new Faker();

    public abstract E getEntity();

    public abstract D getDto();

    public abstract T getTransferDto();

    public Long randomLong() {
        return FAKER.random().nextLong();
    }

    public Integer randomInt() {
        return FAKER.random().nextInt(0, 100);
    }

    public Short randomShort() {
        return FAKER.random().nextInt(0, 100).shortValue();
    }

    public String randomString() {
        return FAKER.name().fullName();
    }

    public BigDecimal randomBigDecimal() {
        return BigDecimal.valueOf(FAKER.random().nextDouble());
    }

    public String getUniqueString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}

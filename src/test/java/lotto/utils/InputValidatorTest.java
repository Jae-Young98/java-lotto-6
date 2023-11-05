package lotto.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidatorTest {
    @DisplayName("금액이 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"천원", "오만원", "thousand"})
    void wrongInputMoney(String money) {
        assertThatThrownBy(() -> InputValidator.validateInputMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자만 입력 가능합니다.");
    }

    @DisplayName("당첨 번호에 공백이 있다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3, ,5,6", "1,,3,4,5,6"})
    void hasBlankInputWinningNumber(String input) {
        // given
        String[] inputWinningNumber = input.split(",");

        // when, then
        assertThatThrownBy(() -> InputValidator.validateInputWinningNumber(inputWinningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백은 입력될 수 없습니다.");
    }

    @DisplayName("당첨 번호에 숫자가 아닌 다른 게 있다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2, 3,4,5,6", "1,2,삼,4,5,6", "1,two,3,4,5,6"})
    void isNotDigitInputWinningNumber(String input) {
        // given
        String[] inputWinningNumber = input.split(",");

        // when, then
        assertThatThrownBy(() -> InputValidator.validateInputWinningNumber(inputWinningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45 사이의 숫자만 입력 가능합니다.");
    }

    @DisplayName("보너스 번호에 공백이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ",""})
    void isBlankBonusNumber(String bonusNumber) {
        assertThatThrownBy(() -> InputValidator.validateBonusNumber(bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백은 입력될 수 없습니다.");
    }

    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"일", "twenty", "-"})
    void isNotDigitBonusNumber(String bonusNumber) {
        assertThatThrownBy(() -> InputValidator.validateBonusNumber(bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45 사이의 숫자만 입력 가능합니다.");
    }
}

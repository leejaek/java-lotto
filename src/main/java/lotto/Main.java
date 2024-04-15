package lotto;

import lotto.domain.*;
import lotto.service.Shop;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InputView input = new InputView();

        int purchasedAmount = input.inputPurchaseAmount();
        Purchase lottoPurchase = Shop.createLottoPurchase(purchasedAmount);

        int manualPurchaseCount = input.inputManualPurchaseCount();

        Shop.validateManualPurchaseCount(lottoPurchase, manualPurchaseCount);

        List<String> manualInputLottoNumbers = new ArrayList<>();
        if (manualPurchaseCount > 0) {
            manualInputLottoNumbers.addAll(input.inputManualLottoNumber(manualPurchaseCount));
        }

        List<Lotto> purchasedLotto = Shop.getLottoList(lottoPurchase, manualInputLottoNumbers);

        OutputView.showPurchasedLotto(purchasedLotto, manualPurchaseCount);

        WinningLotto winningLotto = new WinningLotto(
                WinningLotto.getWinningNumbers(input.inputWinningNumbers()),
                WinningLotto.getBonusNumber(input.inputBonusWinningNumber())
        );

        Map<LottoRank, Long> gameResult = LottoGame.getGameResult(purchasedLotto, winningLotto);

        OutputView.showResult(gameResult);
        OutputView.showROR(gameResult, purchasedAmount);
    }
}

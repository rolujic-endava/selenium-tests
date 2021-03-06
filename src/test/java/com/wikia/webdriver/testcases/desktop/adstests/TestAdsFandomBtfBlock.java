package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdSlot;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;

public class TestAdsFandomBtfBlock extends AdsFandomTestTemplate {

  private static final String ASSERT_MESSAGE = "Expected BTF slot to be blocked.";

  @Test(groups = "AdsFandomBtfBlockDesktop")
  public void adsFandomBtfBlockDesktop() {
    itShouldHideBTFSlotsWhenBTFBlockerIsOnPage();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "AdsFandomBtfBlockMobile")
  public void adsFandomBtfBlockMobile() {
    itShouldHideBTFSlotsWhenBTFBlockerIsOnPage();
  }

  private void itShouldHideBTFSlotsWhenBTFBlockerIsOnPage() {
    AdsFandomObject fandomPage = loadPage(FandomAdsDataProvider.PAGE_BTF_BLOCKER);

    fandomPage.triggerOnScrollSlots();

    fandomPage.verifySlot(AdSlot.TOP_LEADERBOARD);

    Assertion.assertTrue(areBtfSlotsHidden(fandomPage), "BTF ads are displayed");
  }

  private boolean areBtfSlotsHidden(AdsFandomObject fandomPage) {
    try {
      Assertion.assertNull(fandomPage.getSlot(AdSlot.TOP_BOXAD), ASSERT_MESSAGE);
      Assertion.assertNull(fandomPage.getSlot(AdSlot.FEED_BOXAD), ASSERT_MESSAGE);
      Assertion.assertNull(fandomPage.getSlot(AdSlot.INCONTENT_BOXAD), ASSERT_MESSAGE);
      Assertion.assertNull(fandomPage.getSlot(AdSlot.BOTTOM_LEADERBOARD), ASSERT_MESSAGE);
    } catch (AssertionError ae) {
      Log.log("Btf ads are displayed", ae, true);
      return false;
    }
    return true;
  }
}

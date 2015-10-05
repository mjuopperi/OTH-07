import org.junit.Before;
import org.junit.Test;
import template.PuuttuvaArvoException;
import template.Template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/*
public class TemplateTest {
    @Test
    public void yksiMuuttuja() {
        Template template = new Template("Terve, ${nimi}");
        template.korvaa("nimi", "Ilari");
        assertEquals("Terve, Ilari", template.evaluoi());
    }
    @Test
    public void uusiArvo() {
        Template template = new Template("Terve, ${nimi}");
        template.korvaa("nimi", "Olga");
        assertEquals("Terve, Olga", template.evaluoi());
    }
    @Test
    public void uusiTemplate() {
        Template template = new Template("Soon nääs moro, ${nimi}");
        template.korvaa("nimi", "Olga");
        assertEquals("Soon nääs moro, Olga", template.evaluoi());
    }
    @Test
    public void montaMuuttuja() {
        Template template = new Template("${yyy}, ${kaa}, ${koo}");
        template.korvaa("yyy", "1");
        template.korvaa("kaa", "2");
        template.korvaa("koo", "3");
        assertEquals("1, 2, 3", template.evaluoi());
    }
    @Test
    public void tunematonMuuttuja() {
        Template template = new Template("Terve, ${nimi} ${sukunimi}");
        template.korvaa("nimi", "Aukusti");
        template.korvaa("Olematon", "Tätä ei käytetä");
        assertEquals("Terve, Aukusti ${sukunimi}", template.evaluoi());
    }
}
*/

public class TemplateTest {
    private Template template;

    @Before
    public void setup() {
        template = new Template("${yyy}, ${kaa}, ${koo}, ${nee}");
        template.korvaa("yyy", "1");
        template.korvaa("kaa", "2");
        template.korvaa("koo", "3");
    }

    @Test (expected = PuuttuvaArvoException.class)
    public void montaMuuttuja() {
        assertEquals("1, 2, 3, ${nee}", template.evaluoi());
    }

    @Test
    public void tunematonMuuttuja() {
        template.korvaa("nee", "4");
        template.korvaa("Olematon", "Tätä ei käytetä");
        assertEquals("1, 2, 3, 4", template.evaluoi());
    }

    @Test
    public void puuttuvastaPoikkeus() {
        try {
            new Template("Terve, ${Olematon}").evaluoi();
            fail("evaluoi() metodin pitäisi heittää poikkeus jos muuttujalle ei ole asetettu arvoa");
        } catch(PuuttuvaArvoException e) {
            assertEquals("Muuttujalla ${Olematon} ei ole arvoa.", e.getMessage());
        }
    }

}
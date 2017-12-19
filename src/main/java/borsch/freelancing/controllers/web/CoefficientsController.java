package borsch.freelancing.controllers.web;

import borsch.freelancing.services.Coefficients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by olehkurpiak on 19.12.2017.
 */
@Controller
@RequestMapping("/coefficients")
public class CoefficientsController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("coefficients", Coefficients.COEFFICIENTS_MAP);

        return "index/coefficients";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String update(
            @RequestParam("code") String code,
            @RequestParam("value") float value
    ) {
        Coefficients.setCoefficient(code, value);

        return "redirect:/coefficients";
    }
}

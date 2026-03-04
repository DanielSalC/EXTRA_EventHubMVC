package es.daw.eventhubmvc.controller;

import es.daw.eventhubmvc.entity.User;
import es.daw.eventhubmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/edit")
    public String editProfile(
            Principal principal,
            Model model
    ){

        // Principal solo me da el nombre del usuario autenticado
        // Podría utiliar el repo de usuario y a través de principal.getName() obtener
        // la entidad User... noooooooooooooooooo
        // no usarmos un repo directamente en un controlador, usamos un servicio con la
        // lógica de negocio!!!!
        User user =  userService.findByUsername(principal.getName());

        // pendiente!!!
        //
        // 1. crear el objeto dto con los campos del entity user

        // 2. pasar al model el dto como setAttribute

        // 3. return de la vista th
        return "profile/edit";




    }

    public String updateProfile(){
        return null;
    }

}

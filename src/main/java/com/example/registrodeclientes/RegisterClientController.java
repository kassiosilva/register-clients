package com.example.registrodeclientes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class RegisterClientController {
  List<Client> clients = new ArrayList<>();

  @GetMapping("/register")
  public String registerForm(Model model) {
    model.addAttribute("client", new Client());
    return "register";
  }

  @GetMapping("/clients")
  public String getClient(String cpf, Model model) {
    if (cpf != null && cpf.isEmpty()) {
      System.out.println("AQUI");
      List<Client> filteredClients = clients.stream().
              filter(client -> client.getCpf().equals(cpf)).collect(Collectors.toList());

      model.addAttribute("clients", filteredClients);
    } else {
      System.out.println("VAZIO!");
      model.addAttribute("clients", clients);
    }

    return "result";
  }

  @PostMapping("/clients")
  public String registerSubmit(@ModelAttribute Client client, Model model) {

    clients.add(client);

    model.addAttribute("clients", clients);

    return "result";
  }
}

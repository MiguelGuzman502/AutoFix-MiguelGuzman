package org.AutoFix.dao;
import org.AutoFix.model.UsuarioSesion;
public interface LoginDao { UsuarioSesion autenticar(String usuario, String password); }

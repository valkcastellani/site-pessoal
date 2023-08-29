import React from "react";
import { ChevronDown, LocationOutline, MailOutline, PhonePortraitOutline, CalendarOutline, LogoFacebook, LogoTwitter, LogoInstagram } from "react-ionicons";

export const SidebarInfo = () => {
    return (
        <>
            <div className="sidebar-info">
                <figure className="avatar-box">
                    <img src="../assets/images/my-avatar.png" alt="Valk Castellani" width="80" />
                </figure>
                <div className="info-content">
                    <h1 className="name" title="Valk Castellani">Valk Castellani</h1>
                    <p className="title">Desktop & Web Developer</p>
                </div>
                <button className="info_more-btn" data-sidebar-btn>
                    <span>Show Contacts</span>
                    <ChevronDown />
                </button>
            </div>
            <div className="sidebar-info_more">
                <div className="separator"></div>
                <ul className="contacts-list">
                    <li className="contact-item">
                        <div className="icon-box">
                            <MailOutline />
                        </div>
                        <div className="contact-info">
                            <p className="contact-title">Email</p>
                            <a href="mailto:valkcastellani@gmail.com" className="contact-link">valkcastellani@gmail.com</a>
                        </div>
                    </li>
                    <li className="contact-item">
                        <div className="icon-box">
                            <PhonePortraitOutline />
                        </div>
                        <div className="contact-info">
                            <p className="contact-title">Phone</p>
                            <a href="tel:+5521998280801" className="contact-link">+55 (21) 99828-0801</a>
                        </div>
                    </li>
                    <li className="contact-item">
                        <div className="icon-box">
                            <CalendarOutline />
                        </div>
                        <div className="contact-info">
                            <p className="contact-title">Birthday</p>
                            <time dateTime="1975-11-05">November 5, 1975</time>
                        </div>
                    </li>
                    <li className="contact-item">
                        <div className="icon-box">
                            <LocationOutline />
                        </div>
                        <div className="contact-info">
                            <p className="contact-title">Location</p>
                            <address>Rio de Janeiro, Brazil</address>
                        </div>
                    </li>
                </ul>
                <div className="separator"></div>
                <ul className="social-list">
                    <li className="social-item">
                        <a href="#" className="social-link">
                            <LogoFacebook />
                        </a>
                    </li>
                    <li className="social-item">
                        <a href="#" className="social-link">
                            <LogoTwitter />
                        </a>
                    </li>
                    <li className="social-item">
                        <a href="#" className="social-link">
                            <LogoInstagram />
                        </a>
                    </li>
                </ul>
            </div >
        </>
    );  
}

export const Sidebar = () => {
    return (
        <aside className="sidebar" data-sidebar>
            <SidebarInfo />
        </aside>
    );
}